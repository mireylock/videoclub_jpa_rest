import {Component, EventEmitter, Input, OnInit} from '@angular/core';
import { Categoria} from "../categoria";
import {CategoriaService} from "../categoria.service";
import { MdbModalRef, MdbModalService } from 'mdb-angular-ui-kit/modal';
import {ModalComponent} from "../../modal/modal.component";
import {PeliculaService} from "../../pelicula/pelicula.service";
import {ModalService} from "../../service/modal.service";
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-index',
  templateUrl: './index.component.html',
  styleUrls: ['./index.component.css']
})
export class IndexComponent implements OnInit {

  categorias: Categoria[] = [];

  @Input() confirm: EventEmitter<boolean> = new EventEmitter<boolean>();

  constructor(public categoriaService:CategoriaService, private modalService:MdbModalService,
              private modalService1:ModalService) { }


  ngOnInit(): void {
    this.categoriaService.getAll().subscribe((data: Categoria[])=>{
      this.categorias= data;
      console.log(this.categorias);
    })
  }

  deleteCategoria(id: any){
    this.modalService.open(ModalComponent);

    this.modalService1.confirm$.subscribe( (confirm:boolean) => {
      if (confirm) {
        this.categoriaService.delete(id).subscribe(res => {
          this.categorias = this.categorias.filter(cat => cat.id !== id);
          console.log('Categoria id =' + id + ' eliminada satisfactoriamente!');
        })
      }
    })

  }



}

