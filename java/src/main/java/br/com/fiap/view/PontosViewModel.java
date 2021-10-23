package br.com.fiap.view;

import static br.com.fiap.util.DateUtils.getLocalDate;

import br.com.fiap.model.Ponto;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Data;

@Data
public class PontosViewModel {

    public static final Double MINUTES_TO_HOUR = 60d;

    private LocalDate data;
    private int quantidadeMarcacoes;
    private List<PontoViewModel> itens;

    public PontosViewModel(LocalDate data, List<PontoViewModel> itens) {
        this.data = data;
        this.quantidadeMarcacoes = itens.size();
        this.itens = itens;
    }

    public String getHorasTrabalhadas() {
        if (isPossuiBatidaImpar()) {
            return null;
        }
        long totalMinutes = 0;
        for (int i = 0; i < itens.size(); i = i+2) {
            totalMinutes += ChronoUnit.MINUTES.between(itens.get(i).getMarcacao(), itens.get(i+1).getMarcacao());
        }
        return LocalTime.MIN.plus(Duration.ofMinutes(totalMinutes)).toString();
    }

    public boolean isPossuiBatidaImpar() {
        return this.itens.size() % 2 == 1 ? true : false;
    }

    public static List<PontosViewModel> parse(List<Ponto> pontos) {
        pontos.sort(Comparator.comparing(Ponto::getMarcacao));
        var datas = pontos.stream().map(a -> getLocalDate(a.getMarcacao())).collect(Collectors.toSet());
        var itens = pontos.stream().map(PontoViewModel::new).collect(Collectors.toList());
        return datas.stream()
            .map(a -> new PontosViewModel(a, itens.stream().filter(b -> getLocalDate(b.getMarcacao()).equals(a)).collect(Collectors.toList())))
            .collect(Collectors.toList());
    }

}
