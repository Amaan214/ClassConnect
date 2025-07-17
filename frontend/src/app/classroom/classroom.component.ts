import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { Classroom } from './classroom';
import { ClassroomService } from './classroom.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-classroom',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './classroom.component.html',
  styleUrls: ['./classroom.component.css']
})
export class ClassroomComponent implements OnInit {

  constructor(
    private classService: ClassroomService,
    private router: Router
  ) { }

  classroom: Classroom[] = [];
  newCls: Classroom = {
    id: 0,
    grade: '',
    section: ''
  };

    // This ensures that class list is show without explicitly defining it
    ngOnInit(): void {
      this.getAllClass();
    }

  getAllClass() {
    this.classService.getAllClassroom().subscribe((response) => {
      this.classroom = response;
    });
  }

  getClassById(id: number) {
    this.classService.getClassById(id).subscribe((response) => {
      this.classroom.forEach((c, index) => {
        if (c.id == id) this.classroom[index] = response;
      });
    });
  }
// --------------------------------------------------------------ADD METHOD----------------------------------------------------------------
  addClassroom() {
    const classToAdd = {
      grade: this.newCls.grade,
      section: this.newCls.section
    };

    this.classService.addClassroom(classToAdd).subscribe((respone) => {
      console.log("Class added successfully", respone);
      this.classroom.push(respone);
      this.getAllClass();

      // This resets the form
      this.newCls = {
        id: 0,
        grade: '',
        section: ''
      };
    });
  }

  // ----------------------------------------------------------------------UPDATE METHOD----------------------------------------------------------------
 
  updateClassroom(cId: number){
    this.router.navigate(['classroom/update', cId])
  }

  // ---------------------------------------------------------------------------DELETE METHOD--------------------------------------------------------------------
  delClassroom(id: number) {    
    this.classService.deleteClassroom(id).subscribe({
      next: () => {
        console.log("Class deleted successfully");
        this.getAllClass();  // Re-fetch the updated list after deletion
      },
      error: (error) => {
        console.error('Id not found', error);
        // Optionally, re-add the classroom if deletion failed
        this.getAllClass();
      }
    });
  }
  
  confirmDelete(id: number) {
    const confirmed = confirm("Are you sure you want to delete this classroom?");
    if (confirmed) {
      this.delClassroom(id);
    }
  }

  showDetails(id: number){
    this.classService.getFullDetailsById(id).subscribe((details) => {
      this.router.navigate(['classroom/details', id]);
    })
  }
  
}
