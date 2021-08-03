export interface Author {
    id: number ;
    firstName: string;
    lastName: string;
    dateOfBirth: string;
}

export interface Book {
    id: number | undefined | null;
    author: Author;
    title: string;
    bookType: BookType;
    publicationDate: string;
    quantity: number;
    description: string;
    imageUrl: string;
}

export interface BookType {
    id: number;
    type: string;
}

export interface Image {
    url: string;
}

export interface Credentials {
    email: string;
    password: string;
}

export interface UserResponse {
    email: string;
    token: string;
    role: string;
}

export interface User {
    id: number,
    email: string,
    password: string,
    firstName: string,
    lastName: string,
    role: Role
}


export interface Role {
  id: number;
  roleName: string;
}

export interface UserDetails {
  id: number;
  email: string;
  password: string;
  firstName: string;
  lastName: string;
  role: Role;
  dateJoined: string;
}
