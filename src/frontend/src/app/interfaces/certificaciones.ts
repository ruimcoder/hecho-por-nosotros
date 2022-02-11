export interface Certificaciones {
   id?:number,
   emprendimiento_id: number,
   certificados?: Array< { //campo opcional
           image: string,
            type:string
        }>
       
 }
    