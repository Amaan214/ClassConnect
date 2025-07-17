import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Student } from './student';

@Injectable({
  providedIn: 'root'
})
export class StudentService {

  apiurl: string = 'http://localhost:8080/student';

  constructor(private httpClient: HttpClient) { }

  getAllStudent(): Observable<Student[]> {
    return this.httpClient.get<Student[]>(this.apiurl + "/all");
  }

  getStudentById(id: number): Observable<Student> {
    return this.httpClient.get<Student>(this.apiurl + '/' + id);
  }

  addStudent(std : Student): Observable<Student> {
    return this.httpClient.post<Student>(this.apiurl + '/add', std);
  }

  deleteStudent(id: number): Observable<Student> {
    return this.httpClient.delete<Student>(this.apiurl + '/delete/' + id);
  }

  updateStudent(id: number, std: Student): Observable<Student> {
    return this.httpClient.put<Student>(this.apiurl + '/update/' + id, std);
  }
}
