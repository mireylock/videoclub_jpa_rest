import { Component } from '@angular/core';
import {Categoria} from "../../categoria/categoria";
import {Peliculaservice}

@Component({
  selector: 'app-index',
  templateUrl: './index.component.html',
  styleUrls: ['./index.component.css']
})
export class IndexComponent {
  peliculas: Categoria[] = [];

  constructor(public peliculaservice:Peliculaservice) { }

  ngOnInit(): void {
    this.peliculaservice.getAll().subscribe((data: Categoria[])=>{
      this.peliculas= data;
      console.log(this.peliculas);
    })
  }

  deleteCategoria(id: any){
    this.peliculaservice.delete(id).subscribe(res => {
      this.peliculas = this.peliculas.filter(cat => cat.id !== id);
      console.log('Categoria id =' + id + ' eliminada satisfactoriamente!');
    })
  }

}
