import { Component, OnInit, TemplateRef, ViewChild } from '@angular/core';
import { AuthService } from '../services/auth.service';
import { FormControl } from '@angular/forms';
import { Router } from '@angular/router';
import { ReactiveFormsModule } from '@angular/forms';
import { NgbModal, ModalDismissReasons } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-restablecer',
  templateUrl: './restablecer.component.html',
  styleUrls: ['./restablecer.component.scss'],
  providers: [AuthService],
})
export class RestablecerComponent implements OnInit {
  userEmail = new FormControl('');
  @ViewChild('confirmationModal') confirmationModal!: TemplateRef<unknown>;
  constructor(private authSvc: AuthService, private router: Router, private modalService: NgbModal) { }

  ngOnInit(): void {
  }

  async onReset() {
    try {
      const email = this.userEmail.value;
      await this.authSvc.resetPassword(email);
      this.modalService.open(this.confirmationModal).result.then(
        result => {
          if (result === 'cerro') {
            console.log('click')
          }
        },
        reason => {
          console.log(`Dismissed ${this._getDismissReason(reason)}`);
        });
      //redirect to login
      this.router.navigate(['/login']);
    }
    catch (error) { console.log(error) };
  }
  _getDismissReason(reason: unknown): string {
    if (reason === ModalDismissReasons.ESC) {
      return 'by pressing ESC';
    } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
      return 'by clicking on a backdrop';
    } else {
      return `with: ${reason}`;
    }
  }
}
