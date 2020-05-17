> covid <- read.csv("c:/covid.csv", sep=",")
> row.names(covid) <- covid$State
> covid <- covid[,2:4]
> covid_matrix <- data.matrix(covid)
> covid_heatmap <- heatmap(covid_matrix, Rowv=NA, Colv=NA, col = cm.colors(256), scale="column", margins=c(5,10))
> covid_heatmap <- heatmap(covid_matrix, Rowv=NA, Colv=NA, col = heat.colors(256), scale="column", margins=c(5,10))