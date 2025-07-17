import { Component, OnInit } from '@angular/core';
import { ClassroomService } from '../classroom/classroom.service';
import { ActivatedRoute, Router } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-classroom-details',
  imports: [CommonModule],
  templateUrl: './classroom-details.component.html',
  styleUrl: './classroom-details.component.css'
})
export class ClassroomDetailsComponent implements OnInit {
  constructor(
    private classService: ClassroomService,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  details: any = null;
  error: string | null = null;

  ngOnInit(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    if(id){
      this.classService.getFullDetailsById(id).subscribe({
        next: (data) => {
          this.details = data;
        },
        error: (err) => {
          this.error = 'Class loading error!';
        }
      });
    }
    else{
      this.error = 'Invalid Id';
    }
  }
}
