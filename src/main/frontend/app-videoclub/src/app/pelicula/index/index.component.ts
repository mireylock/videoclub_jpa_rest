import { Component } from '@angular/core';
import {Pelicula} from "../../pelicula/pelicula";
import {PeliculaService} from "../pelicula.service";
import {ModalComponent} from "../../modal/modal.component";
import { MdbModalRef, MdbModalService } from 'mdb-angular-ui-kit/modal';


@Component({
  selector: 'app-index',
  templateUrl: './index.component.html',
  styleUrls: ['./index.component.css']
})
export class IndexComponent {
  peliculas: Pelicula[] = [];

  modalRef: MdbModalRef<ModalComponent> | null = null;


  constructor(public peliculaservice:PeliculaService, private modalService:MdbModalService) { }

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

  openModal() {
    this.modalRef = this.modalService.open(ModalComponent);
    // this.modalRef = this.modalService.open(ModalComponent, { data: { tipo: tipoElemento } });
  }


}
