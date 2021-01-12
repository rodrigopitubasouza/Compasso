import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { NotFoundComponent } from './shared/components/not-found/not-found.component';

const routes: Routes = [
  {
    path: 'usuarios',
    loadChildren: () =>
      import('./modules/user/user.module').then((module) => module.UserModule),
  },
  { path: '', redirectTo: '/usuarios', pathMatch: 'full' },
  {
    path: '**', component: NotFoundComponent
  }
];
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
