export interface Tecnica {
    id:number,
    emprendimiento_id: number,
    texto: string,
    url_video: string,
    tipo: "tecnica",
    imagenes: Array < {
        image:string
        type: string
    }> 
}
