import {Component, EventEmitter, Inject, Input, Output} from '@angular/core';
import { MdbModalRef } from 'mdb-angular-ui-kit/modal';
import {ModalService} from "../service/modal.service";


@Component({
  selector: 'app-modal',
  templateUrl: './modal.component.html',
  styleUrls: ['./modal.component.css'],
})
export class ModalComponent {
  confirm: EventEmitter<boolean> = new EventEmitter<boolean>();

  constructor(public modalRef: MdbModalRef<ModalComponent>, private modalService: ModalService) {}

  confirmAction() {
    this.modalService.confirmAction(true);
    this.modalRef.close();
  }


}
