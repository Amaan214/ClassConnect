import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Teacher } from './teacher';

@Injectable({
  providedIn: 'root'
})
export class TeacherService {

  apiurl: string = 'http://localhost:8080/teacher';

  constructor(private httpClient: HttpClient) { }

  getAllTeacher(): Observable<Teacher[]> {
    return this.httpClient.get<Teacher[]>(this.apiurl + "/all");
  }

  getTeacherById(id: number): Observable<Teacher> {
    return this.httpClient.get<Teacher>(this.apiurl + '/' + id);
  }

  addTeacher(teach: Teacher): Observable<Teacher> {
    return this.httpClient.post<Teacher>(this.apiurl + '/add', teach);
  }

  deleteTeacher(id: number): Observable<Teacher> {
    return this.httpClient.delete<Teacher>(this.apiurl + '/delete/' + id);
  }

  updateTeacher(id: number, tch: Teacher): Observable<Teacher> {
    return this.httpClient.put<Teacher>(this.apiurl + '/update/' + id, tch);
  }

}

