import { NgModule } from '@angular/core';
import { MatCardModule, MatListModule, MatToolbarModule, MatIconModule, MatButtonModule, MatInputModule } from '@angular/material';

@NgModule({
  exports: [
    MatCardModule,
    MatIconModule,
    MatButtonModule,
    MatToolbarModule,
    MatListModule,
    MatInputModule
  ]
})
export class MaterialModule {}
