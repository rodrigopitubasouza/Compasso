import { Component, OnInit } from '@angular/core';
import { TableColumn } from 'src/app/shared/models/table-column';
import { UsersService } from './services/users.service';

import { take } from 'rxjs/operators';


@Component({
  selector: 'app-user',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.scss']
})
export class UsersComponent implements OnInit {

  public name!: string;
  public userCols!: TableColumn[];
  public users!: any[];

  public loadingUser = false;

  constructor(private usersService: UsersService) {}

  ngOnInit(): void {
    this.buildCols();
  }

  public search(): void {
    if (this.name) {
      this.loadingUser = true;
      this.usersService
        .findByUserName(this.name)
        .pipe(take(1))
        .subscribe(
          (response) => {
            this.users = [response];
            this.loadingUser = false;
          },
          (error) => {
            this.loadingUser = false;
          }
        );
      }
    }

  private buildCols(): void {
    this.userCols = [
      new TableColumn('Id', 'id'),
      new TableColumn('Nome', 'name'),
      new TableColumn('Login', 'login'),
      new TableColumn('Repositórios', 'public_repos'),
      new TableColumn('Seguidores', 'followers'),
      new TableColumn('Seguindo', 'following'),
    ];
  }

}
