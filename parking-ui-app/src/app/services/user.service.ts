import {EventEmitter, Injectable} from '@angular/core';
import {HttpClient, HttpErrorResponse, HttpHeaders} from "@angular/common/http";
import {Observable, throwError} from "rxjs";
import {catchError} from "rxjs/operators";
import {$} from "protractor";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  refresh: EventEmitter<any> = new EventEmitter();

  constructor(private httpClient: HttpClient) {
  }


  public getEmployee(id: String): Observable<Employee> {
    console.log("Employee Info Request");
    return this.httpClient.get<Employee>('/api/employee/'+id)
      .pipe(
        catchError((error: HttpErrorResponse) => {
          console.log("No Information Available for User {}", id);
          window.location.reload();
          return throwError(error);
        })
      );
  }

  public reloadMainList() {
    return this.refresh
  }
}
