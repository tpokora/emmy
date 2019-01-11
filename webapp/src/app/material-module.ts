import { NgModule } from '@angular/core';
import { MatCardModule,
  MatListModule,
  MatToolbarModule,
  MatIconModule,
  MatButtonModule,
  MatInputModule,
  MatExpansionModule
} from '@angular/material';

@NgModule({
  exports: [
    MatCardModule,
    MatIconModule,
    MatButtonModule,
    MatToolbarModule,
    MatListModule,
    MatInputModule,
    MatExpansionModule
  ]
})
export class MaterialModule {}
