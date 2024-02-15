import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PeliculaRoutingModule } from './pelicula-routing.module';
import { CreateComponent } from './create/create.component';
import { IndexComponent } from './index/index.component';
import { EditComponent } from './edit/edit.component';


@NgModule({
  declarations: [
    CreateComponent,
    IndexComponent,
    EditComponent
  ],
  imports: [
    CommonModule,
    PeliculaRoutingModule
  ]
})
export class PeliculaModule { }
