import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormProductComponent } from './form-product.component';
import {ReactiveFormsModule} from "@angular/forms";
import {FormProductRoutingModule} from "./form-product-routing.module";
import {HttpClientModule} from "@angular/common/http";
import {FormProductService} from "./form-product.service";
import { FormErrorMessageComponent } from './form-error-message/form-error-message.component';



@NgModule({
  declarations: [
    FormProductComponent,
    FormErrorMessageComponent
  ],
  imports: [
    CommonModule,
    FormProductRoutingModule,
    HttpClientModule,
    ReactiveFormsModule
  ],
  providers:[FormProductService]
})
export class FormProductModule { }
