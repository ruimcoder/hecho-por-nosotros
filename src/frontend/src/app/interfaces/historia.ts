export interface Historia {
   id:number,
    emprendimiento_id: number,
    texto: string,
    url_video: string,
    tipo: 'historia',
    imagenes: Array<{
        image: string,
        type: string
    }>
}
