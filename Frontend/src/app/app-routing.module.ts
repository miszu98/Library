import { MainComponent } from './Components/main/main.component';
import { ForWorkersComponent } from './Components/for-workers/for-workers.component';
import { LoginComponent } from './Components/login/login.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthorsComponent } from './Components/authors/authors.component';
import { BooksComponent } from './Components/books/books/books.component';

const routes: Routes = [

  {path: 'authors', component: AuthorsComponent},
  {path: 'books', component: BooksComponent},
  {path: 'login', component: LoginComponent},
  {path: 'for-workers', component: ForWorkersComponent},
  {path: 'main', component: MainComponent},
  {path: 'books/:firstName/:lastName', component: BooksComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
