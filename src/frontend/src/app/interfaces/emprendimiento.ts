import { Meta } from "@angular/platform-browser";
import { Certificaciones } from "./certificaciones";
import { Contacto } from "./contacto";
import { Historia } from "./historia";
import { Proveedor } from "./proveedor";
import { Punto } from "./punto";
import { Tecnica } from "./tecnica";

export interface Emprendimiento {
    id: number,
    nombre: string,
    publicado: boolean,
    slogan: string,
    genero: string,
    registro: number,
    tipo: string,
    pertenece: string,
    exporta: boolean,
    count_empleados: number,
    count_mujeres: number,
    count_jovenes: number,
    count_colaboracion: number,
    count_mujeres_colaboracion: number,
    count_jovenes_colaboracion: number,
    comentario_meta: string,
    contacto: Contacto,
    historia: Historia,
    certificados: any,
    tecnica: Tecnica
    logo: {
        image: string,
        type: string
    },
    portada: {
        image: string,
        type: string
    },
    puntosDeVenta: Array<Punto>,
    proveedores: Array<Proveedor>,
    metas: Meta
}

