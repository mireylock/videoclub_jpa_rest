import { Component } from '@angular/core';
import {Pelicula} from "../../pelicula/pelicula";
import {PeliculaService} from "../pelicula.service";

@Component({
  selector: 'app-index',
  templateUrl: './index.component.html',
  styleUrls: ['./index.component.css']
})
export class IndexComponent {
  peliculas: Pelicula[] = [];

  constructor(public peliculaservice:PeliculaService) { }

  ngOnInit(): void {
    this.peliculaservice.getAll().subscribe((data: Pelicula[])=>{
      this.peliculas= data;
      console.log(this.peliculas);
    })
  }

  deletePelicula(id: any){
    this.peliculaservice.delete(id).subscribe(res => {
      this.peliculas = this.peliculas.filter(cat => cat.id !== id);
      console.log('Pelicula id =' + id + ' eliminada satisfactoriamente!');
    })
  }

}
