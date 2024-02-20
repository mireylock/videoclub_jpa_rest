import { Injectable } from '@angular/core';
import {Subject} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class ModalService {

  private confirmSubject = new Subject<boolean>();

  constructor() {
  }

  confirm$ = this.confirmSubject.asObservable();

  confirmAction(confirm: boolean) {
    this.confirmSubject.next(confirm);
  }

}
