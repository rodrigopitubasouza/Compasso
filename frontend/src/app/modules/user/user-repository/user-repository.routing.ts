import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { Routes } from '@angular/router';
import { UserRepositoryComponent } from './user-repository.component';

const routes: Routes = [
  {
    path: '',
    component: UserRepositoryComponent,
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class UserRepositoryRouting {}
