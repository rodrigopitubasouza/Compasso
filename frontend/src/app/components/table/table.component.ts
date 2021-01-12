import { Component, ContentChild, Input, OnInit, TemplateRef } from '@angular/core';

import { TableColumn } from 'src/app/shared/models/table-column';

@Component({
  selector: 'app-table',
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.scss'],
})
export class TableComponent implements OnInit {

  @Input()
  public cols!: TableColumn[];

  @Input()
  public values!: any[];

  @Input()
  public hasActions = false;

  @Input()
  public actionsWidth = '10%';

  @Input()
  public actions!: TemplateRef<any>;

  @Input()
  public emptyMessage = 'Nenhum Registro Encontrado';

  @Input()
  public loading = false;

  constructor() {}

  ngOnInit(): void {}

  public getValue(value: any, col: TableColumn): any {
    if (value && col.field) {
      if (col.pipe) {
        return col.pipe.transform(
          this.getValueWithSubPropertyBinds(value, col.field)
        );
      } else {
        return this.getValueWithSubPropertyBinds(value, col.field);
      }
    } else {
      return null;
    }
  }

  private getValueWithSubPropertyBinds(value: any, field: string): any {
    const binds = field.split('.');
    let newValue = value;
    if (binds) {
      for (const bind of binds) {
        newValue = newValue[bind];
      }
    } else {
      newValue = value[field];
    }
    return newValue != null ? newValue : '-';
  }
}
