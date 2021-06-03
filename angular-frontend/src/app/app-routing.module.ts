import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  {path: 'listProducts',
  loadChildren: () => import('./list-product/list-product.module').then(m=>m.ListProductModule)
  },
  {path: '', pathMatch: 'full', redirectTo: 'listProducts'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
