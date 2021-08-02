import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { AuthorsComponent } from './Components/authors/authors.component';

import {MatCardModule} from '@angular/material/card';
import {MatDialogModule} from '@angular/material/dialog';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {MatButtonModule} from '@angular/material/button';
import { AuthorAddFormComponent } from './Components/authors/forms/author-add-form/author-add-form.component';
import {MatFormFieldModule} from '@angular/material/form-field';
import { MatNativeDateModule } from '@angular/material/core';
import { MatInputModule } from '@angular/material/input';
import {MatIconModule} from '@angular/material/icon';
import { MAT_DATE_LOCALE } from '@angular/material/core';
import { AuthorDeleteFormComponent } from './Components/authors/forms/author-delete-form/author-delete-form.component';
import {MatSelectModule} from '@angular/material/select';
import { AuthorUpdateFormComponent } from './Components/authors/forms/author-update-form/author-update-form.component';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatTabsModule} from '@angular/material/tabs';
import {MatProgressSpinnerModule} from '@angular/material/progress-spinner';
import {MatGridListModule} from '@angular/material/grid-list';

import { HttpClientModule } from '@angular/common/http';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { BooksComponent } from './Components/books/books/books.component';
import { BookAddFormComponent } from './Components/books/forms/book-add-form/book-add-form.component';
import { BookDeleteFormComponent } from './Components/books/forms/book-delete-form/book-delete-form.component';
import { BookUpdateFormComponent } from './Components/books/forms/book-update-form/book-update-form.component';
import { LoginComponent } from './Components/login/login.component';
import { ForWorkersComponent } from './Components/for-workers/for-workers.component';
import { MainComponent } from './Components/main/main.component';
import { authInterceptorsProviders } from './Interceptors/AuhtInterceptor/auth.interceptor';
import { RegisterComponent } from './Components/register/register/register.component';
import { UserAddFormComponent } from './Components/for-workers/forms/user-add-form/user-add-form/user-add-form.component';
import { BookDescComponent } from './Components/books/forms/bookDescription/book-desc/book-desc.component';
@NgModule({
  declarations: [
    AppComponent,
    AuthorsComponent,
    AuthorAddFormComponent,
    AuthorDeleteFormComponent,
    AuthorUpdateFormComponent,
    BooksComponent,
    BookAddFormComponent,
    BookDeleteFormComponent,
    BookUpdateFormComponent,
    LoginComponent,
    ForWorkersComponent,
    MainComponent,
    RegisterComponent,
    UserAddFormComponent,
    BookDescComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    NgbModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,

    MatCardModule,
    MatDialogModule,
    MatDatepickerModule,
    MatButtonModule,
    MatFormFieldModule,
    MatNativeDateModule,
    MatInputModule,
    MatIconModule,
    MatSelectModule,
    MatToolbarModule,
    MatTabsModule,
    MatProgressSpinnerModule,
    MatGridListModule

    

  ],
  providers: [{ provide: MAT_DATE_LOCALE, useValue: "en-Gb" }, authInterceptorsProviders],
  bootstrap: [AppComponent]
})
export class AppModule { }
