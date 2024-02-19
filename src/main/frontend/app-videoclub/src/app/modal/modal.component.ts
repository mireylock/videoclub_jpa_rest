import {Component, Inject} from '@angular/core';
import { MdbModalRef } from 'mdb-angular-ui-kit/modal';
import { CategoriaService} from "../categoria/categoria.service";
import { PeliculaService} from "../pelicula/pelicula.service";
import { MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'app-modal',
  templateUrl: './modal.component.html',
  styleUrls: ['./modal.component.css']
})
export class ModalComponent {
  constructor(public modalRef: MdbModalRef<ModalComponent>
  ) {}

}
