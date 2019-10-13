import {EventEmitter, Injectable} from '@angular/core';
import {HttpClient, HttpErrorResponse, HttpHeaders} from "@angular/common/http";
import {Observable, throwError} from "rxjs";
import {catchError} from "rxjs/operators";
import {$} from "protractor";

@Injectable({
    providedIn: 'root'
})
export class EmployeeService {

    refresh: EventEmitter<any> = new EventEmitter();

    constructor(private httpClient: HttpClient) {
    }

    public loadAllEmployee(): Observable<Page> {
        return this.httpClient.get<Page>('/api/employee')
    }

    public registerEmployee(employee: Employee): Observable<Page> {
        const headers = new HttpHeaders({'Content-Type': 'application/json; charset=utf-8'});
        console.log("Parking Request");
        return this.httpClient.post<Page>('/api/employee', JSON.stringify(employee), {headers: headers})
            .pipe(
                catchError((error: HttpErrorResponse) => {
                    console.log("Parking Full");
                    window.location.reload();
                    return throwError(error);
                })
            );
    }

    public reloadMainList() {
        return this.refresh
    }
}
