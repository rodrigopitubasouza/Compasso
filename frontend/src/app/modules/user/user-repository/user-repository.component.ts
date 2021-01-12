import { Component, OnInit } from '@angular/core';
import { TableColumn } from 'src/app/shared/models/table-column';
import { UserRepositoryService } from './services/user-repository.service';

import { take } from 'rxjs/operators';
import { Location } from '@angular/common';
import { ActivatedRoute } from '@angular/router';


@Component({
  selector: 'app-user-repository',
  templateUrl: './user-repository.component.html',
  styleUrls: ['./user-repository.component.scss']
})
export class UserRepositoryComponent implements OnInit {

  public repositoryCols!: TableColumn[];
  public repositories!: any;
  public userName!: string;

  public loadingRepository = false;

  constructor(private userRepositoryService: UserRepositoryService,
              private location: Location,
              private activatedRoute: ActivatedRoute) { }

  ngOnInit(): void {
    this.buildCols();
    this.getUserNameInUrlParam();
    this.search();
  }

  private search(): void {
    if (this.userName) {
      this.loadingRepository = true;
      this.userRepositoryService
        .findByUserName(this.userName)
        .pipe(take(1))
        .subscribe(
          (response) => {
            this.repositories = response;
            this.loadingRepository = false;
          },
          (error) => {
            this.loadingRepository = false;
          }
        );
    }
  }

  private buildCols(): void {
    this.repositoryCols = [
      new TableColumn('Id', 'id'),
      new TableColumn('Nome', 'name'),
      new TableColumn('Url', 'html_url'),
      new TableColumn('Linguagem', 'language')
    ];
  }

  public back(): void {
    this.location.back();
  }

  private getUserNameInUrlParam(): void {
    this.userName = this.activatedRoute.snapshot.params.param;
  }

}
