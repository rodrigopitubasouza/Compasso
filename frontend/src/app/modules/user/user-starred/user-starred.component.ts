import { Component, OnInit } from '@angular/core';
import { TableColumn } from 'src/app/shared/models/table-column';
import { UserStarredService } from './services/user-starred.service';

import { take } from 'rxjs/operators';
import { Location } from '@angular/common';
import { ActivatedRoute } from '@angular/router';


@Component({
  selector: 'app-user-starred',
  templateUrl: './user-starred.component.html',
  styleUrls: ['./user-starred.component.scss']
})
export class UserStarredComponent implements OnInit {

  public starredCols!: TableColumn[];
  public stars!: any;
  public userName!: string;

  public loadingStarred = false;

  constructor(private userStarredService: UserStarredService,
              private location: Location,
              private activatedRoute: ActivatedRoute) { }

  ngOnInit(): void {
    this.buildCols();
    this.getUserNameInUrlParam();
    this.search();
  }

  private search(): void {
    if (this.userName) {
      this.loadingStarred = true;
      this.userStarredService
        .findByUserName(this.userName)
        .pipe(take(1))
        .subscribe(
          (response) => {
            this.stars = response;
            this.loadingStarred = false;
          },
          (error) => {
            this.loadingStarred = false;
          }
        );
    }
  }

  private buildCols(): void {
    this.starredCols = [
      new TableColumn('Id', 'id'),
      new TableColumn('Nome', 'name'),
      new TableColumn('Url', 'html_url'),
    ];
  }

  public back(): void {
    this.location.back();
  }

  private getUserNameInUrlParam(): void {
    this.userName = this.activatedRoute.snapshot.params.param;
  }
}
