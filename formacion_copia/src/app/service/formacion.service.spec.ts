import { TestBed } from '@angular/core/testing';

import { FormacionServiceService } from './formacion.service';

describe('FormacionServiceService', () => {
  let service: FormacionServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(FormacionServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
