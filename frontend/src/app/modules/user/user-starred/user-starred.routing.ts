import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { Routes } from '@angular/router';
import { UserStarredComponent } from './user-starred.component';

const routes: Routes = [
  {
    path: '',
    component: UserStarredComponent,
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class UserStarredRouting {}
