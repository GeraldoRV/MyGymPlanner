import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Global} from '../utilities/global';
import {TypeClassAdminDto} from '../dto/type-class-admin.dto';

@Injectable({
  providedIn: 'root'
})
export class TypeClassService {
  private readonly baseUrl: string;

  constructor(private _http: HttpClient, private _global: Global) {
    this.baseUrl = _global.IpAddress + 'type-class';
  }

  getAllTaughtClass() {
    return this._http.get<TypeClassAdminDto[]>(this.baseUrl);
  }
}
