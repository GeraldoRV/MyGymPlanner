import {Pipe, PipeTransform} from '@angular/core';

@Pipe({name: 'evenOrOdd'})
export class EvenOrOdd implements PipeTransform {
  transform(value: any[], filter: string) {
    if (!value || (filter !== 'even' && filter !== 'odd')) {
      return value;
    }
    return value.filter((item, index) => filter === 'even' ? index % 2 === 1 : index % 2 === 0);
  }
}
