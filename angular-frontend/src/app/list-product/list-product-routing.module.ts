import { NgModule } from '@angular/core';
import {ListProductComponent} from "./list-product.component";
import {RouterModule, Routes} from "@angular/router";

const routes: Routes = [
  {path: '' , component: ListProductComponent}
]


@NgModule({
  imports: [
    RouterModule.forChild(routes)
  ],
  exports: [
    RouterModule
  ]
})
export class ListProductRoutingModule { }
