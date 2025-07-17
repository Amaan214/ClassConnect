import { Component } from '@angular/core';
import { SubjectService } from '../subject/subject.service';
import { ActivatedRoute, Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { Subject } from '../subject/subject';

@Component({
  selector: 'app-update-subject',
  imports: [FormsModule],
  templateUrl: './update-subject.component.html',
  styleUrl: './update-subject.component.css'
})
export class UpdateSubjectComponent {

  constructor(private subjServ: SubjectService,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  id!: number;
  editSubj: Subject = {
    name: '',
    code: ''
  };

  ngOnInit() : void {
    this.id = Number(this.route.snapshot.paramMap.get('id'));

    this.subjServ.getSubjById(this.id).subscribe({
      next: (data) => {
        this.editSubj = data;
      },
      error: (err) => {
        console.error('Failed to load subject', err);
      }
    });
  }

  updSubject() {
    this.subjServ.updateSubject(this.id, this.editSubj).subscribe({
      next: () => {
        console.log('Subject udpated successfully');
        this.router.navigate(['/subject']); // redirect to list page
      },
      });
    }
}
