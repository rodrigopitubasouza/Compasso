import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { UserRepositoryService } from './user-repository.service';
import { environment } from 'src/environments/environment';
import { AppConstants } from 'src/app/shared/core/app-constants';

describe('UserRepositoryService', () => {
  let service: UserRepositoryService;
  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule]
    });
    service = TestBed.inject(UserRepositoryService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify();
  });

    it('Should return the repositories of user, if exist username', async () => {
        const dummyRepos = [{
          id: 1,
          name: 'teste 1',
          html_url: 'teste.com',
          language: 'JavaScript'
        },
        {
          id: 2,
          name: 'teste 2',
          html_url: 'teste2.com',
          language: 'Java'
        }
      ];

        service.findByUserName('testeUsuarioExistente').subscribe(repos => {
          expect(repos.length).toBe(2);
          expect(repos).toEqual(dummyRepos);
        })

        const request = httpMock.expectOne(environment.applicationUrl.concat('/').concat(AppConstants.USER_CONTROLLER)
        .concat('/testeUsuarioExistente').concat(AppConstants.REPOSITORY_CONTROLLER));

        expect(request.request.method).toBe('GET');

        request.flush(dummyRepos);
    });

    it('Should return empty, if not exist username', async () => {
      const dummyRepos = [];

      service.findByUserName('usuarioTotalmenteInexistente').subscribe(repos => {
        expect(repos).toEqual([]);
      })

      const request = httpMock.expectOne(environment.applicationUrl.concat('/').concat(AppConstants.USER_CONTROLLER)
      .concat('/usuarioTotalmenteInexistente').concat(AppConstants.REPOSITORY_CONTROLLER));

      expect(request.request.method).toBe('GET');

      request.flush(dummyRepos);
   });
  });
