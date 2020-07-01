import { Injectable } from '@angular/core';
import {HttpRequest, HttpHandler, HttpEvent, HttpInterceptor} from '@angular/common/http';
import { Observable } from 'rxjs';
import { AuthenticationService } from '../services/authentication.service';

@Injectable()
export class TokenInterceptor implements HttpInterceptor {

  constructor(private auth: AuthenticationService) {}

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    
    if(this.auth.isUserSignedIn()) {
        req = req.clone({
            setHeaders: {
                Authorization: `Bearer ${this.auth.getCurrentUser().jwt}`
            }
        });
    }

    return next.handle(req); //passing control to the next interceptor
  }

  
}