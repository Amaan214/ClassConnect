import { Component } from '@angular/core';
import { Classroom } from '../classroom/classroom';
import { ClassroomService } from '../classroom/classroom.service';
import { ActivatedRoute, Router } from '@angular/router';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-update-classroom',
  imports: [FormsModule],
  templateUrl: './update-classroom.component.html',
  styleUrls: ['./update-classroom.component.css']
})
export class UpdateClassroomComponent {

  constructor(private classService: ClassroomService,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  id!: number;
  editcls: Classroom = {
    grade: '',
    section: ''
  };

  ngOnInit(): void {
    this.id = Number(this.route.snapshot.paramMap.get('id'));

    this.classService.getClassById(this.id).subscribe({
      next: (data) => {
        this.editcls = data;
      },
      error: (err) => {
        console.error('Failed to load classroom', err);
      }
    });
  }

  updClassroom() {
    this.classService.updateClass(this.id, this.editcls).subscribe({
      next: () => {
        console.log('Class udpated successfully');
        this.router.navigate(['/classroom']); // redirect to list page
      },
      });
    }
  }

