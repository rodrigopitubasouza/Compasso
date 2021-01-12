import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { Routes } from '@angular/router';

const routes: Routes = [
  {
    path: '',
    children: [
      {
        path: 'detail',
        loadChildren: () =>
          import('./user-detail/user-detail.module').then((module) => module.UserDetailModule),
      },
      {
        path: ':param/repos',
        loadChildren: () =>
          import('./user-repository/user-repository.module').then((module) => module.UserRepositoryModule),
      },
      {
        path: ':param/starred',
        loadChildren: () =>
          import('./user-starred/user-starred.module').then((module) => module.UserStarredModule),
      },
      {
        path: '',
        loadChildren: () =>
          import('./users/users.module').then((module) => module.UsersModule),
      },
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class UserRouting { }
