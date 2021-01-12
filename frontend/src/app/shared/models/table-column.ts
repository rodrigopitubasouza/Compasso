export class TableColumn {
    public header: string;
    public field: string;
    public width?: string;
    public align?: string;
    public pipe?: any;

    constructor(
      header: string,
      field: string,
      width?: string,
      align?: string,
      pipe?: any
    ) {
      this.header = header;
      this.field = field;
      this.width = width;
      this.align = align;
      this.pipe = pipe;
    }
  }
