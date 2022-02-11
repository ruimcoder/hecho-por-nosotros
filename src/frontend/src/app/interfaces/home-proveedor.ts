export interface HomeProveedor {
    
        id: number,
        nombre: string,
        direccion: string,
        pais: string,
        ciudad:  string,
        cp: 5400,
        materiales: [
            {
                id:number,
                nombre: string
            },
            {
                id:number,
                nombre: string
            },
            {
                id: number,
                nombre: string
            }
        ],
        otroEspecificar: string
    
}
