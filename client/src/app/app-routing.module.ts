import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ProductEditorComponent } from './products/editor/product-editor.component';
import { ProductListComponent } from './products/list/product-list.component';

const routes: Routes = [
    { path: 'product/new', component: ProductEditorComponent},
    { path: 'product/:id/edit', component: ProductEditorComponent},
    { path: 'products', component: ProductListComponent},
    { path: '**', component: ProductListComponent},
];

@NgModule({
    imports: [
        RouterModule.forRoot(routes),
    ],
    exports: [RouterModule]
})
export class AppRoutingModule { }
