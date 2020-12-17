import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Global} from '../utilities/global';
import {TypeClassAdminDto} from '../dto/type-class-admin.dto';
import {TypeClass} from '../model/type-class';
import {TypeClassLeaderDto} from '../dto/type-class-leader.dto';

@Injectable()
export class TypeClassService {
  private readonly baseUrl: string;

  constructor(private _http: HttpClient, private _global: Global) {
    this.baseUrl = _global.IpAddress + 'type-class';
  }

  getAllTaughtClass() {
    return this._http.get<TypeClassAdminDto[]>(this.baseUrl + '/admin');
  }

  getAllClassesForLeader(id: number) {
    return this._http.get<TypeClassLeaderDto[]>(this.baseUrl + '/team/' + id);
  }

  getAll() {
    return this._http.get<TypeClass[]>(this.baseUrl);
  }

  addTeam(typeClassAdminDto: TypeClassAdminDto, teamId: number) {
    return this._http.put(this.baseUrl + '/add-team/' + teamId, typeClassAdminDto);
  }
}

