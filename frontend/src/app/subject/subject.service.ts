import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Subject } from './subject';

@Injectable({
  providedIn: 'root'
})
export class SubjectService {

  apiurl: string = 'http://localhost:8080/subject';
  constructor(private httpClient: HttpClient) { }

  getAllSubjects():Observable<Subject[]> {
    return this.httpClient.get<Subject[]>(this.apiurl + "/all");
  }

  getSubjById(id: number):Observable<Subject>{
      return this.httpClient.get<Subject>(this.apiurl + '/' + id);
    }
  
    addSubject(subj: Subject):Observable<Subject>{
      return this.httpClient.post<Subject>(this.apiurl + '/add',subj);
    }
  
    deleteClassroom(id:number):Observable<Subject>{
      return this.httpClient.delete<Subject>(this.apiurl+'/delete/'+id);
    }
  
    updateSubject(id:number, subj: Subject):Observable<Subject>{
      return this.httpClient.put<Subject>(this.apiurl+'/update/'+id, subj);
    }
}
