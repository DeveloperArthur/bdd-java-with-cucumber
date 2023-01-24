package com.example.demo.service;

import com.example.demo.dto.NovoLanceDto;
import com.example.demo.model.Lance;
import com.example.demo.model.Leilao;
import com.example.demo.model.Usuario;
import com.example.demo.repository.LanceRepository;
import com.example.demo.repository.LeilaoRepository;
import com.example.demo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LanceService {

    @Autowired
    public LanceRepository lancesRepo;

    @Autowired
    public UsuarioRepository usuariosRepo;

    @Autowired
    public LeilaoRepository leiloesRepo;

    public boolean propoeLance(NovoLanceDto lanceDto, String nomeUsuario) {

        Usuario usuario = usuariosRepo.getUserByUsername(nomeUsuario);
        Lance lance = lanceDto.toLance(usuario);

        Leilao leilao =  this.getLeilao(lanceDto.getLeilaoId());

        if (leilao.propoe(lance)) {
            lancesRepo.save(lance);
            return true;
        }

        return false;
    }

    public Leilao getLeilao(Long leilaoId) {
        return leiloesRepo.getOne(leilaoId);
    }

    //TODO: deixar o método em um service apenas de Usuarios
    public Usuario buscaUsuario(String nome){
        return usuariosRepo.getUserByUsername(nome);
    }

    public void salvaUsuario(Usuario usuario) {
        usuariosRepo.saveAndFlush(usuario);
    }
}