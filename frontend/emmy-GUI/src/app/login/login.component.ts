import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  form: FormGroup;
  public loginInvalid = false;
  private formSubmitAttempt = false;

  constructor(private formBuilder: FormBuilder) {
    this.form = this.formBuilder.group({
      username: ['', Validators.email],
      password: ['', Validators.required]
    });
  }

  ngOnInit(): void {
  }

  async onSubmit(): Promise<void> {
    this.loginInvalid = false;
    this.formSubmitAttempt = false;
    if (this.form.valid) {
      try {
        const username = this.form.get('username')?.value;
        const password = this.form.get('password')?.value;
        // TODO: call for log in user
      } catch (err) {
        this.loginInvalid = true;
      }
    } else {
      this.formSubmitAttempt = true;
    }
  }

}
