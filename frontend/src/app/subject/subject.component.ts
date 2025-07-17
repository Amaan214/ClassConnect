import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SubjectService } from './subject.service';
import { Subject } from './subject';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-subject',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './subject.component.html',
  styleUrls: ['./subject.component.css']
})
export class SubjectComponent implements OnInit {
  constructor(private subjServ: SubjectService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.getAllSubjects();
  }

  subjList: Subject[] = [];

  newSubj: Subject = {
    id: 0,
    name: '',
    code: '',
    teacher: undefined,
    classrooms: undefined
  };

  getAllSubjects() {
    this.subjServ.getAllSubjects().subscribe((data) => {
      this.subjList = data;
    });
  }

  getSubjById(id: number) {
    this.subjServ.getSubjById(id).subscribe((data) => {
      this.subjList.forEach((c, index) => {
        if (c.id == id) this.subjList[index] = data;
      });
    });
  }

  // --------------------------------------------------------------ADD METHOD----------------------------------------------------------------
  addSubject() {
    const SubjToAdd = {
      name: this.newSubj.name,
      code: this.newSubj.code
    }
    this.subjServ.addSubject(SubjToAdd).subscribe((data) => {
      console.log("Subject added successfully", data);
      this.subjList.push(data);
      this.getAllSubjects();

      // This resets the form
      this.newSubj = {
        id: 0,
        name: '',
        code: '',
      }
    });
  }

  // ----------------------------------------------------------------------UPDATE METHOD----------------------------------------------------------------
  UpdateSubject(sId: number) {
    this.router.navigate(['subject/update', sId]);
  }

  // ---------------------------------------------------------------------------DELETE METHOD--------------------------------------------------------------------
  delSubject(id: number) {
    this.subjServ.deleteClassroom(id).subscribe({
      next: () => {
      console.log("Subject deleted successfully");
      this.getAllSubjects(); // Re-fetch the updated list after deletion
      },

      error: (err) => {
        console.error('Id not found');
        this.getAllSubjects();
      }
  });
  }

  confirmDel(id: number){
    const isConfirmed = confirm("Are you sure you want to delete this classroom?");
    if(isConfirmed) {
      this.delSubject(id);
    }
  }

}
