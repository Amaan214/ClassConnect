import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Classroom } from './classroom';

@Injectable({
  providedIn: 'root'
})
export class ClassroomService {

  apiurl: string = 'http://localhost:8080/classroom';
  constructor(private httpClient: HttpClient) { }
  getAllClassroom(): Observable<Classroom[]> {
    return this.httpClient.get<Classroom[]>(this.apiurl + "/all");
  }

  getClassById(id: number):Observable<Classroom>{
    return this.httpClient.get<Classroom>(this.apiurl + '/' + id);
  }

  addClassroom(cls: Classroom):Observable<Classroom>{
    return this.httpClient.post<Classroom>(this.apiurl + '/add',cls);
  }

  deleteClassroom(id:number):Observable<Classroom>{
    return this.httpClient.delete<Classroom>(this.apiurl+'/delete/'+id);
  }

  updateClass(id:number, cls:Classroom):Observable<Classroom>{
    return this.httpClient.put<Classroom>(this.apiurl+'/update/'+id, cls);
  }

  getFullDetailsById(id: number): Observable<any>{
    return this.httpClient.get<any>(this.apiurl + '/details/' + id);
  }
}
