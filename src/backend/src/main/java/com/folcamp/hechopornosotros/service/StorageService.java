package com.folcamp.hechopornosotros.service;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.folcamp.hechopornosotros.models.dto.EmprendimientoFileDTO;
import com.folcamp.hechopornosotros.models.dto.InformacionFileDTO;
import com.folcamp.hechopornosotros.models.entity.*;
import com.folcamp.hechopornosotros.models.mapper.EmprendimientoFileMapper;
import com.folcamp.hechopornosotros.models.mapper.InformacionFileMapper;
import com.folcamp.hechopornosotros.models.repositories.*;
import com.folcamp.hechopornosotros.util.UploadBeanImage;
import com.folcamp.hechopornosotros.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;

@Service
public class StorageService {

    @Autowired
    private AmazonS3 s3client;
    @Autowired
    FileRepository fileRepository;
    @Value("${amazonProperties.endpointUrl}")
    private String defaultEndpointUrl;
    @Value("${amazonProperties.bucketName}")
    private String bucketName;
    @Autowired
    private EmprendimientoRepository emprendimientoRepository;
    @Autowired
    private EmprendimientoFileRepository emprendimientoFileRepository;
    @Autowired
    private EmprendimientoFileMapper emprendimientoFileMapper;
    @Autowired
    private InformacionRepository informacionRepository;
    @Autowired
    private InformacionFileRepository informacionFileRepository;
    @Autowired
    private InformacionFileMapper informacionFileMapper;


    @Transactional
    public EmprendimientoFileDTO storeImageEmprendimiento(UploadBeanImage uploadBeanImage, Long empr_id) {
        FileEntity fileEntity = uploadImage(uploadBeanImage);

        EmprendimientoEntity emprendimientoEntity = emprendimientoRepository.findById(empr_id).get();

        EmprendimientoFileEntity emprendimientoFileEntity = new EmprendimientoFileEntity();
        emprendimientoFileEntity.setFileEntity(fileEntity);
        emprendimientoFileEntity.setEmprendimientoEntity(emprendimientoEntity);
        emprendimientoFileEntity.setType(uploadBeanImage.getType());

        emprendimientoFileRepository.save(emprendimientoFileEntity);

        EmprendimientoFileDTO emprendimientoFileDTO =
                emprendimientoFileMapper.mapEmprendimientoFileEntityToEmprendimientoFileDTO(emprendimientoFileEntity);

        return emprendimientoFileDTO;
    }

    @Transactional
    public InformacionFileDTO storeImageInformacion(UploadBeanImage uploadBeanImage, Long infoId) {
        FileEntity fileEntity = uploadImage(uploadBeanImage);

        InformacionEntity informacionEntity = informacionRepository.findById(infoId).get();

        InformacionFileEntity informacionFileEntity = new InformacionFileEntity();
        informacionFileEntity.setFileEntity(fileEntity);
        informacionFileEntity.setInformacionEntity(informacionEntity);
        informacionFileEntity.setType(uploadBeanImage.getType());

        informacionFileRepository.save(informacionFileEntity);

        InformacionFileDTO informacionFileDTO =
                informacionFileMapper.mapInformacionFileEntityToInformacionFileDTO(informacionFileEntity);

        return informacionFileDTO;
    }

    private FileEntity uploadImage(UploadBeanImage uploadBeanImage){
        String fileName = System.currentTimeMillis() + "_file";
        String fileUrl = "";
        File file = Utils.getImageFromBase64(uploadBeanImage.getImage(), fileName);
        try {
            fileUrl = defaultEndpointUrl + "/" + file.getName();
            uploadFileTos3bucket(file.getName(), file);
            file.delete();

        } catch (Exception e) {
            file.delete();
            throw new RuntimeException(e.getMessage());
        }

        FileEntity fileEntity = new FileEntity();
        fileEntity.setName(file.getName());
        fileEntity.setUrl(fileUrl);

        fileRepository.save(fileEntity);
        return fileEntity;

    }

    private void uploadFileTos3bucket(String fileName, File file) {
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setCacheControl("51635000");
        metadata.setExpirationTime(Utils.getTwentyYearsFromNow());

        s3client.putObject(new PutObjectRequest(bucketName, fileName, file)
                .withCannedAcl(CannedAccessControlList.PublicRead).withMetadata(metadata));
    }

    public String deleteFileToS3(String keyName) {
        try {
            FileEntity fileEntity = fileRepository.findByName(keyName).get();
            fileRepository.delete(fileEntity);
            s3client.deleteObject(new DeleteObjectRequest(bucketName, keyName));

        } catch (AmazonServiceException e) {
            //Amazon S3 no puede procesar la petición
            e.printStackTrace();
        } catch (SdkClientException e) {
            //No se pudo contactar con Amazon S3,cliente no pudo analizar la respuesta
            e.printStackTrace();
        }

        return "Eliminado";

    }

}