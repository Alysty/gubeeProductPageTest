import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormProductComponent } from './form-product.component';
import {ReactiveFormsModule} from "@angular/forms";



@NgModule({
  declarations: [
    FormProductComponent
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule
  ]
})
export class FormProductModule { }
