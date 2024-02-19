import {Component, OnInit} from '@angular/core';
import { Categoria} from "../categoria";
import {CategoriaService} from "../categoria.service";
import { MdbModalRef, MdbModalService } from 'mdb-angular-ui-kit/modal';
import {ModalComponent} from "../../modal/modal.component";

@Component({
  selector: 'app-index',
  templateUrl: './index.component.html',
  styleUrls: ['./index.component.css']
})
export class IndexComponent implements OnInit {

  categorias: Categoria[] = [];

  constructor(public categoriaService:CategoriaService, private modalService:MdbModalService) { }

  modalRef: MdbModalRef<ModalComponent> | null = null;

  openModal(tipoElemento: string) {
    this.modalRef = this.modalService.open(ModalComponent, { data: { tipo: tipoElemento } });
  }


  ngOnInit(): void {
    this.categoriaService.getAll().subscribe((data: Categoria[])=>{
      this.categorias= data;
      console.log(this.categorias);
    })
  }

  deleteCategoria(id: any){
    this.categoriaService.delete(id).subscribe(res => {
      this.categorias = this.categorias.filter(cat => cat.id !== id);
      console.log('Categoria id =' + id + ' eliminada satisfactoriamente!');
    })
  }


}

