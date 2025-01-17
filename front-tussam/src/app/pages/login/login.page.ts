import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import { BackTussamService } from 'src/app/services/back-tussam.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.page.html',
  styleUrls: ['./login.page.scss'],
})
export class LoginPage implements OnInit {

  //Creamos un formulario con dos campos, email y password
  form = new FormGroup({
    email: new FormControl('', [Validators.required, Validators.email]),
    password: new FormControl('', [Validators.required]),
  })

  constructor(
    private backSvc:BackTussamService
  ) { }

  ngOnInit() {
  }

  submit() {
    // Si el formulario es válido, mostramos los datos por consola
    if (this.form.valid) {
      const email = this.form.value.email;
      const password = this.form.value.password;
      console.log(email,password)
  
      if (email && password) {
        this.backSvc.login(email, password).subscribe(
          response => {
            // Manejar la respuesta del servidor aquí (éxito o error)
            console.log(response);
          },
          error => {
            // Manejar el error de la suscripción
            console.error(error);
          }
        );
      }
    }
  }
  

  forgotPassword() {
    //Mostramos un mensaje en consola
    console.log('Forgot password');
  }
}
